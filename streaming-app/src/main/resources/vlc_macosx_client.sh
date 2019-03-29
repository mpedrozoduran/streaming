#!/bin/sh
ADDRESS=$1
PORT=$2
/Applications/VLC.app/Contents/MacOS/VLC "rtsp://$ADDRESS:$PORT/test.sdp"