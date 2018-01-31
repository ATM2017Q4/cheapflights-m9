package com.cheapflights.ui.utils.webdrivertools;

public abstract class WaitDecorator implements IWait {
    protected Wait wait;

    public WaitDecorator() {

    }

    public WaitDecorator(Wait wait) {
        this.wait = wait;
    }

    @Override
    public abstract boolean waitForExpectedCondition();


}
