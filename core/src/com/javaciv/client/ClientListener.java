package com.javaciv.client;

import java.util.EventListener;

import com.javaciv.events.KeyPressedEvent;

public interface ClientListener extends EventListener {
    public void keyPressed(KeyPressedEvent e);
}
