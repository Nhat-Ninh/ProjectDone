package com.javaweb.config.filter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${api.prefix}")
    private String apiPrefix;

    private final UserDetailsService userDetailsService;
    private final com.javaweb.security.utils.JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (isBypassToken(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }

            final String token = authHeader.substring(7);
            final String userName = jwtTokenUtil.extractUserName(token);
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    private boolean isBypassToken(@NonNull HttpServletRequest request) {
        String normalizedPrefix = apiPrefix.startsWith("/") ? apiPrefix : "/" + apiPrefix;
        List<Pair<String, String>> protectedEndpoints = Arrays.asList(
                Pair.of(String.format("%s/buildings", normalizedPrefix), "POST"),
                Pair.of(String.format("%s/buildings/*", normalizedPrefix), "DELETE"),
                Pair.of(String.format("%s/buildings/*/staffs", normalizedPrefix), "GET"),
                Pair.of(String.format("%s/user/*", normalizedPrefix), "PUT"),
                Pair.of(String.format("%s/user/change-password/*", normalizedPrefix), "PUT"),
                Pair.of(String.format("%s/user/password/*/reset", normalizedPrefix), "PUT"),
                Pair.of(String.format("%s/user/profile/*", normalizedPrefix), "PUT"),
                Pair.of(String.format("%s/user", normalizedPrefix), "DELETE"),
                Pair.of(String.format("%s/assignments", normalizedPrefix), "POST"),
                Pair.of(String.format("%s/assignment", normalizedPrefix), "POST"),
                Pair.of(String.format("%s/customer/*", normalizedPrefix), "DELETE"),
                Pair.of(String.format("%s/transactions", normalizedPrefix), "POST"),
                Pair.of(String.format("%s/transactions/*", normalizedPrefix), "DELETE")
        );

        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (Pair<String, String> endpoint : protectedEndpoints) {
            if (antPathMatcher.match(endpoint.getFirst(), request.getServletPath())
                    && request.getMethod().equalsIgnoreCase(endpoint.getSecond())) {
                return false;
            }
        }
        return true;
    }
}
