package com.jx372.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
//	String value() default "USER"; @Auth("admin") 처럼 사용 하고 싶을때(role="admin") 말고
//	String role() default "USER";
	Role role() default Role.USER;
	public enum Role {ADMIN, USER}
	
}
