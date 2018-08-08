package ru.bellintegrator.practice.response;

public class ErrorView {
    public final String error;

    public ErrorView(String error) {
        this.error = error;
    }

    public ErrorView() {
        error = null;
    }
}
