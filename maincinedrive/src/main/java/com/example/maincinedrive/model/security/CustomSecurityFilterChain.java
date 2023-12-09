// package com.example.maincinedrive.model.security;

// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// import org.springframework.security.web.util.matcher.OrRequestMatcher;

// import jakarta.servlet.http.HttpServletRequest;

// import jakarta.servlet.Filter;
// // Asegúrate de tener la importación correcta

// import java.util.Arrays;
// import java.util.List;

// public class CustomSecurityFilterChain implements SecurityFilterChain {

// private final OrRequestMatcher requestMatcher;
// private final List<Filter> filters;

// public CustomSecurityFilterChain(String pattern, Filter... filters) {
// this.requestMatcher = new OrRequestMatcher(Arrays.asList(new
// AntPathRequestMatcher(pattern)));
// this.filters = Arrays.asList(filters);
// }

// @Override
// public boolean matches(HttpServletRequest request) {
// return requestMatcher.matches(request);
// }

// @Override
// public List<Filter> getFilters() {
// return filters;
// }
// }
