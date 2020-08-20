package com.pc.android.program.sdk.internal.reactnative;

import com.pc.android.program.sdk.PointCheckoutEnvironment;
import com.pc.android.program.sdk.PointCheckoutLanguage;
import com.pc.android.program.sdk.PointCheckoutTheme;

public class RNConfig {
    public PointCheckoutEnvironment environment;
    public String authToken;
    public PointCheckoutTheme theme;
    public PointCheckoutLanguage language;
    public boolean handleExceptions;

    public static Builder Builder(){
        return new Builder();
    }

    public static class Builder {
        private RNConfig instance;

        public Builder(){
            instance = new RNConfig();
        }

        public Builder environment(PointCheckoutEnvironment environment){
            instance.environment = environment;
            return this;
        }

        public Builder authToken(String authToken){
            instance.authToken = authToken;
            return this;
        }

        public Builder theme(PointCheckoutTheme theme){
            instance.theme = theme;
            return this;
        }

        public Builder language(PointCheckoutLanguage language){
            instance.language = language;
            return this;
        }

        public Builder handleExceptions(boolean handleExceptions){
            instance.handleExceptions = handleExceptions;
            return this;
        }

        public RNConfig build(){
            return this.instance;
        }
    }
}
