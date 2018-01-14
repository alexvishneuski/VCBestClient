package com.github.alexvishneuski.vkbestclient.repository.networking.modelparsing;

public interface IParser<T> {

    T parse(Class<T> pClazz);
}
