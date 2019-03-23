#!/bin/sh

function log() {
    echo -e '\n'$1'\n'
}

function readgoon() {
    echo -e '\n'$1
    read
}

NOW_DATE=`date +%Y-%m-%d`

# git tag
echo -e '\n$ git tag'
git tag

# 输入参数
echo
until [ -n ''$VERSION_NAME ]; do
	read -p "input version name: v" VERSION_NAME
done;

# git tag -a v1.0.0 -m "2000-01-01 publish v1.0.0"
echo -e '\n\n$ git tag -a v'$VERSION_NAME' -m "'$NOW_DATE' publish v'$VERSION_NAME'"'
readgoon 'verify and press any key to go on, press Ctrl + C to exit.'
git tag -a v$VERSION_NAME -m $NOW_DATE' publish v'$VERSION_NAME

# git tag
echo -e '\n$ git tag'
git tag

# git push --tag
echo -e '\n\n$ git push --tag'
readgoon 'verify and press any key to go on, press Ctrl + C to exit.'
git push --tag

readgoon 'please press any key to exit.'
exit
