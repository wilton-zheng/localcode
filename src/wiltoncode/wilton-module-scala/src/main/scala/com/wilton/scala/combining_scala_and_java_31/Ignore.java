package com.wilton.scala.combining_scala_and_java_31;/* an annotation in Java notation */

import java.lang.annotation.*; // This is Java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Ignore { }
