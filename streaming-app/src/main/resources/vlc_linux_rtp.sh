#!/bin/sh
ADDRESS=$1
PORT=$2
vlc -vvv /home/mduran/Downloads/the_isle/the_isle.mp4 --sout "#rtp{dst=$ADDRESS,port=1234,sdp=rtsp://localhost:$PORT/test.sdp}"