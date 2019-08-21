package com.sda.javagda21.gotgame.gui;


import com.sda.javagda21.gotgame.model.Map;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("game")
public class ContextClassLoader extends ClassLoader {
    private ClassLoader targetLoader;
    Map map = new Map;

    public ContextClassLoader(ClassLoader p) {
        this.targetLoader = Thread.currentThread().getContextClassLoader();
        if (this.targetLoader==null) {
            throw new IllegalStateException("Cannot set up custom class loader: No context class loader set");
        }
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        return targetLoader.loadClass(map);
    }



    }



