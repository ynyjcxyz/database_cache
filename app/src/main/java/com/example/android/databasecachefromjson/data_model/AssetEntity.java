package com.example.android.databasecachefromjson.data_model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class AssetEntity {
    public abstract String token_id();

    public abstract String permalink();

    public abstract String name();

    public abstract String image_url();

    public static Builder builder() {
        return new AutoValue_AssetEntity.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder token_id(String token_id);

        public abstract Builder permalink(String permalink);

        public abstract Builder name(String name);

        public abstract Builder image_url(String image_url);

        public abstract AssetEntity build();
    }
}
