#!/bin/bash

# Script testing the good creation of our oneM2M Architecture

# Turn on verbose and echo.
set -vx

# Set the IP address, Port Number, and Name of the CSE from the command line arguments.
IPADDRESS="127.0.0.1"
PORT="8080"
CSE="~/in-cse"

for Num in 1 2
do
  # Create container under Floor_manager-AE
  echo
  echo ----------Create container Outside_Temp under Floor_manager-AE
  echo `curl -i -X GET -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerOutSide_Temp.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager?fu=1&rty=3&drt=2" -o /dev/null && echo "SUCCESS" || echo "ERROR"`

  echo
  echo ----------Create container under Floor_manager-AE
  echo `curl -i -X GET -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerInside_Temp.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager?fu=1&rty=3&drt=2" -o /dev/null && echo "SUCCESS" || echo "ERROR"`

  echo
  echo ----------Create container under Floor_manager-AE
  echo `curl -i -X GET -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerWindow_position.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager?fu=1&rty=3&drt=2" -o /dev/null && echo "SUCCESS" || echo "ERROR"`

  echo
  echo ----------Create container under Floor_manager-AE
  echo `curl -i -X GET -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerDoor_position.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager?fu=1&rty=3&drt=2" -o /dev/null && echo "SUCCESS" || echo "ERROR"`

  echo
  echo ----------Create container under Floor_manager-AE
  echo `curl -i -X GET -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerPresence_detection.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager?fu=1&rty=3&drt=2" -o /dev/null && echo "SUCCESS" || echo "ERROR"`

  echo
  echo ----------Create container under Floor_manager-AE
  echo `curl -i -X GET -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerAlarm.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager?fu=1&rty=3&drt=2" -o /dev/null && echo "SUCCESS" || echo "ERROR"`


  # Testing content instance data
  echo
  echo ----------Create content instance under each container
  curl -i -X GET -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Outside_Temp/la"


  echo
  echo ----------Create content instance under each container
  curl -i -X GET -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Inside_Temp/la"

  echo
  echo ----------Create content instance under each container
  curl -i -X GET -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Window_Position/la"

  echo
  echo ----------Create content instance under each container
  curl -i -X GET -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Door_Position/la"

  echo
  echo ----------Create content instance under each container
  curl -i -X GET -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Presence_Detection/la"

  echo
  echo ----------Create content instance under each container
  curl -i -X GET -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Alarm/la"

done
