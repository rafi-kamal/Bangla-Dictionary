#!/bin/bash

if [[ -n "$1" ]]; then
	database="$1"
else
	database="dictionary"
fi

if [[ -n "$2" ]]; then
	data="$2"
else
	data="words.txt"
fi

if [ ! -f $data ]; then
	echo "$data does not exist."
	exit 1
fi

rm data.sql
while read line; do
	IFS='|' read -ra words <<< "$line"
	echo "INSERT INTO words (en_word, bn_word) VALUES ('${words[1]}', '${words[2]}');" >> data.sql
done < $data

