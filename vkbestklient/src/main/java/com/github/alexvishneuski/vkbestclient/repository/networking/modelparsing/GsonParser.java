package com.github.alexvishneuski.vkbestclient.repository.networking.modelparsing;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class GsonParser<T> implements IParser<T> {

    private final InputStream mIntputStream;

    public GsonParser(InputStream pIntputStream) {
        mIntputStream = pIntputStream;
    }

    @Override
    public T parse(Class<T> pClazz) {
        Reader reader = new InputStreamReader(mIntputStream);
        return new Gson().fromJson(reader, pClazz);
    }
}
