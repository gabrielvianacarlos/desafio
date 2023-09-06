#!/bin/bash

set +x
set -o errexit

toggle_file=${PLUGIN_FILE}

isExpired () {
    NOW=$(date '+%s')
    EXPIRATION=$(date -d "$@" '+%s')

    if [ "$NOW" -gt "$EXPIRATION" ]; then
        echo 'TRUE'
    else
        echo 'FALSE'
    fi;
}

export -f isExpired

EXPIRED_TOGGLES=$(yq r "$toggle_file" -j 'toggles[*].expiration' | grep -v null | jq -r .[] | xargs -I {} bash -c 'isExpired "$@"' _ {} | grep -n TRUE | cut -f1 -d':' | xargs -I {} echo "{}-1" | bc | xargs -I {} yq r "$toggle_file" toggles[{}].name)

if [ -n "$EXPIRED_TOGGLES" ]; then
    echo "There are toggles to be removed: "
    echo "$EXPIRED_TOGGLES"
    echo "$EXPIRED_TOGGLES" > expired_toggles.out
    exit 1
fi

echo "OK!"
