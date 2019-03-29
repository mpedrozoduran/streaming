#!/bin/sh
ADDRESS=$1
PORT=$2
/Applications/VLC.app/Contents/MacOS/VLC -vvv /Users/mpedrozoduran/Downloads/The_Ninth_Gate_1999/The.Ninth.Gate.1999.720p.BluRay.x264.YIFY.mp4 --sout "#rtp{dst=$ADDRESS,port=1234,sdp=rtsp://localhost:$PORT/test.sdp}"