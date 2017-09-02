#!/bin/bash

OVERLAYS_PATH="app/src/main/assets/overlays"
VARIANTS_PATH="generate_type3"
DEFAULT_TYPE="res"

$VARIANTS_PATH/clean.sh

for package in "$OVERLAYS_PATH/"*; do
    for variant in "$VARIANTS_PATH/"*; do
        if [ -d "$variant" ]; then
            package_name="$(basename "$package")"
            variant_name="$(basename "$variant")"
            source_dir="$package/$DEFAULT_TYPE"
            target_dir="$package/$variant_name"
            echo "Creating $target_dir"
            cp -rf "$source_dir" "$target_dir"

            variant_replacements_path="$VARIANTS_PATH/$variant_name/$package_name"
            if [ -d "$variant_replacements_path" ]; then
                find "$variant_replacements_path" -type f -print0 | while read -d $'\0' replacement; do
                    origin="$(echo "$replacement" | sed "s#$variant_replacements_path#$target_dir#")"
                    echo "Inserting $origin from $replacement"
                    cp -f "$replacement" "$origin"
                done
            fi
        fi
    done
done