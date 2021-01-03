package com.au.compiler;

public class RegisterNumberRange {

    private int fromRegisterNumber;
    private int toRegisterNumber;

    RegisterNumberRange(){}

    public RegisterNumberRange(int fromRegisterNumber, int toRegisterNumber) {
        this.fromRegisterNumber = fromRegisterNumber;
        this.toRegisterNumber = toRegisterNumber;
    }

    public int getFromRegisterNumber() {
        return fromRegisterNumber;
    }

    public void setFromRegisterNumber(int fromRegisterNumber) {
        this.fromRegisterNumber = fromRegisterNumber;
    }

    public int getToRegisterNumber() {
        return toRegisterNumber;
    }

    public void setToRegisterNumber(int toRegisterNumber) {
        this.toRegisterNumber = toRegisterNumber;
    }
}
