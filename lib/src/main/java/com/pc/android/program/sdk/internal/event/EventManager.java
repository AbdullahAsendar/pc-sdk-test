package com.pc.android.program.sdk.internal.event;

import androidx.annotation.Nullable;

import com.pc.android.program.sdk.PointCheckoutEventListener;
import com.pc.android.program.sdk.PointCheckoutEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;

public class EventManager {

    public static EventManager instance = new EventManager();
    private static final PropertyChangeSupport CHANGE_SUPPORT = new PropertyChangeSupport(EventManager.class);

    private EventManager() {
    }

    public void addEventListener(PointCheckoutEvent event, final PointCheckoutEventListener listener) {
        CHANGE_SUPPORT.addPropertyChangeListener(event.name(), new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String value = evt.getNewValue() == null? null : evt.getNewValue().toString();
                listener.onEvent(value);
            }
        });
    }

    public void fireEvent(PointCheckoutEvent event, @Nullable String data) {
        CHANGE_SUPPORT.firePropertyChange(event.name(), null, data);
    }

    public void fireEvent(String event, @Nullable String data) {
        CHANGE_SUPPORT.firePropertyChange(event, null, data);
    }
}
