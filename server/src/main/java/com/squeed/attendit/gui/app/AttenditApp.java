package com.squeed.attendit.gui.app;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.enterprise.context.SessionScoped;

import com.squeed.attendit.gui.view.EventsView;
import com.squeed.attendit.server.dao.RegistrationDAO;
import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SessionScoped
public class AttenditApp extends Application {

    @EJB
    RegistrationDAO dao;

    @Override
    public void init() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        Label header = new Label("Welcome to attendit");
        header.setStyleName("h1");
        layout.addComponent(header);

        layout.addComponent(new EventsView(dao));

        Window mainWindow = new Window("My Vaadin Application", layout);
        setMainWindow(mainWindow);
    }

    @Override
    @Remove
    public void close() {
        super.close();
    }

}
