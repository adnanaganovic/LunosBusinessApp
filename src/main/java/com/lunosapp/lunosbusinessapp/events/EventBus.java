package com.lunosapp.lunosbusinessapp.events;


//SABIRNICA SVIH EVENATA

public class EventBus {

    private final LoginEvent loginEvent = new LoginEvent();
    private final LogoutEvent logoutEvent = new LogoutEvent();
    private final CancelEvent cancelEvent = new CancelEvent();

    public LoginEvent getLoginEvent() {
        return loginEvent;
    }

    public LogoutEvent getLogoutEvent() {
        return logoutEvent;
    }

    public CancelEvent getCancelEvent() {
        return cancelEvent;
    }
}
