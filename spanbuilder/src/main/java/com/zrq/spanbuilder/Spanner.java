package com.zrq.spanbuilder;

import com.zrq.spanbuilder.enums.Bold;

/**
 * Created by llitfkitfk on 11/21/16.
 */

public final class Spanner {

    private Spans spans;

    public Spanner(Builder builder) {
        this.spans = builder.spans;
    }

    public Spans text() {
        return this.spans;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        private SpanBuilder spanBuilder;
        private Spans spans;

        public Builder() {
            this.spans = new Spans();
        }

        public Builder(Spans spans) {
            this.spans = spans;
        }

        public Builder deleteLine() {
            this.spanBuilder.setDeleteLine();
            return this;
        }

        public Builder bold(Bold bold) {
            this.spanBuilder.setTypeface(bold.ordinal());
            return this;
        }

        public Builder size(int textSize) {
            this.spanBuilder.setTextSize(textSize);
            return this;
        }

        public Builder color(int textColor) {
            this.spanBuilder.setTextColor(textColor);
            return this;
        }

        public Builder text(CharSequence text) {
            if(this.spanBuilder != null) {
                this.spans.append(this.spanBuilder);
            }
            this.spanBuilder  = new SpanBuilder(text);
            return this;
        }

        public Spanner build() {
            this.spans.append(this.spanBuilder);
            return new Spanner(this);
        }


    }
}
