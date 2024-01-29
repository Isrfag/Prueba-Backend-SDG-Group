package com.example.ApiDatosPoblacionMundo.mappers;


public interface Mapper<A,B> {

    B mapTo(A a);

    A mapFrom (B b);

}
