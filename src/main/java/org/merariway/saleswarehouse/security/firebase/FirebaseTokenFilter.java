package org.merariway.saleswarehouse.security.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.merariway.saleswarehouse.dto.LoginFirebaseDTO;
import org.merariway.saleswarehouse.entity.UserEntity;
import org.merariway.saleswarehouse.security.EndPointSecurity;
import org.merariway.saleswarehouse.service.auth.AuthFirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Component
public class FirebaseTokenFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final FirebaseAuth firebaseAuth;
    private final AuthFirebaseService service;
    private final Logger lg = Logger.getLogger(FirebaseTokenFilter.class.getName());
    private AntPathMatcher antMatcher = new AntPathMatcher();

    @Autowired
    public FirebaseTokenFilter(FirebaseAuth firebaseAuth, AuthFirebaseService service) {
        this.firebaseAuth = firebaseAuth;
        this.service = service;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        return Arrays.stream(EndPointSecurity.OFFLINE_ACCESS).anyMatch(
                pattern -> antMatcher.match(pattern, path)
        );
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith(BEARER_PREFIX)) {
            errorHttp(response, "No authentication token was provided");
            return;
        }

        String idToken = header.replace(BEARER_PREFIX, "");
        Optional<LoginFirebaseDTO> loginFirebase = validateFirebaseToken(idToken);

        if (loginFirebase.isEmpty()) {
            errorHttp(response, "Your session is invalid");
            return;
        }

        UserEntity user = service.findUserByUID(loginFirebase.get());

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().getRole()))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }


    // METODO => VALIDA CON EL SERVIDOR DE FIREBASE

    private Optional<LoginFirebaseDTO> validateFirebaseToken(String idToken) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);

            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();
            String displayName = decodedToken.getName();

            return Optional.of(new LoginFirebaseDTO(uid, email, displayName));
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private void errorHttp(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"error\": \" " + message + "\"}");
    }
}
