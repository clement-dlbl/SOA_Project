#!/bin/bash

# Script creating our oneM2M Architecture
#me=${0##*/}
#if [ $# -ne  ]; then
#  echo "Usage: $me <IN-CSE IP Address> <IN-CSE Port #> <IN-CSE-BaseName> <MN-CSE-BaseName> <MN-CSE-ID>"
#  exit 1
#fi

# Turn on verbose and echo.
set -vx

# Set the IP address, Port Number, and Name of the CSE from the command line arguments.
IPADDRESS="127.0.0.1"
PORT="8080"
CSE="~/in-cse"

for Num in 1 2
do
  # Create an Floor_manager-AE
  echo
  echo ----------Create Floor_Manager-AE
  curl -i -X POST -H "X-M2M-RI: req001" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=2" -d @payloadFloor${Num}_Manager.xml "http://$IPADDRESS:$PORT/$CSE"

  # Create container under Floor_manager-AE
  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerOutside_Temp.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager"

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerInside_Temp.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager"

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerWindow_position.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager"

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerDoor_position.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager"

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerPresence_detection.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager"

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerAlarm.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager"


  # Create a content instance data
  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_OutsideTemp.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Outside_Temp"

  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_InsideTemp.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Inside_Temp"

  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_WindowPosition.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Window_Position"

  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_DoorPosition.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Door_Position"

  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_Presence.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Presence_Detection"

  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_Alarm.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${Num}_Manager/Alarm"

done
