#!/bin/sh
ADDRESS=$1
PORT=$2
RESOURCE=$3
/Applications/VLC.app/Contents/MacOS/VLC -vvv $RESOURCE --sout "#rtp{dst=$ADDRESS,port=1234,sdp=rtsp://localhost:$PORT/test.sdp}"