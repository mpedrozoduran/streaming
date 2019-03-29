#!/bin/sh
ADDRESS=$1
PORT=$2
vlc "rtsp://$ADDRESS:$PORT/test.sdp"