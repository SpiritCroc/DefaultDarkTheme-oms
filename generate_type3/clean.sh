#!/bin/bash

OVERLAYS_PATH="app/src/main/assets/overlays"

find "$OVERLAYS_PATH" -type d -name "type3_*" -print0 | while read -d $'\0' dir; do
    echo "Removing $dir"
    rm -rf "$dir"
done