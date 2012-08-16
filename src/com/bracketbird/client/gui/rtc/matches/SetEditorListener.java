package com.bracketbird.client.gui.rtc.matches;

/**
 *
 */
public interface SetEditorListener {


    public class SetEditorEvent {
        private SetEditorState.State newState;
        private SetEditorState.State oldState;

        public SetEditorEvent(SetEditorState.State newState, SetEditorState.State oldState) {
            this.newState = newState;
            this.oldState = oldState;
        }

        public SetEditorState.State getNewState() {
            return newState;
        }

        public SetEditorState.State getOldState() {
            return oldState;
        }
    }


    public void onChange(SetEditorEvent event);

}
