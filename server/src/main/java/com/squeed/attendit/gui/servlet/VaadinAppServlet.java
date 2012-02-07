package com.squeed.attendit.gui.servlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.squeed.attendit.gui.app.AttenditApp;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

@WebServlet(urlPatterns = "/*")
public class VaadinAppServlet extends AbstractApplicationServlet {

    @Inject
    AttenditApp application;

    @Override
    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
        return AttenditApp.class;
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {
        return application;
    }

}
