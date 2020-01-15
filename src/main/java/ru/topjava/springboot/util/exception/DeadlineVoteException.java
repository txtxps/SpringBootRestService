package ru.topjava.springboot.util.exception;

public class DeadlineVoteException extends RuntimeException {
    public DeadlineVoteException(String msg) {
        super(msg);
    }
}
