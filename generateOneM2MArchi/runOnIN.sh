#!/bin/bash

# Script creating our oneM2M Architecture
#me=${0##*/}
#if [ $# -ne  ]; then
#  echo "Usage: $me <IN-CSE IP Address> <IN-CSE Port #> <IN-CSE-BaseName> <MN-CSE-BaseName> <MN-CSE-ID>"
#  exit 1
#fi

# Turn on verbose and echo.
set -vx

# Set the IP address, Port FloorNumber, and Name of the CSE from the command line arguments.
IPADDRESS="127.0.0.1"
PORT="8080"
CSE="~/in-cse"

for FloorNum in 1 2
do
  # Create an Floor_manager-AE
  echo
  echo ----------Create Floor_Manager-AE
  curl -i -X POST -H "X-M2M-RI: req001" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=2" -d @payloadFloor${FloorNum}_Manager.xml "http://$IPADDRESS:$PORT/$CSE"

 # Create container ROOM under Floor_manager-AE
  if [[ $FloorNum -eq 1 ]]; then
    echo
    echo ----------Create container under Floor_manager-AE
    ROOM=107   
    else
      echo
      echo ----------Create container under Floor_manager-AE
      ROOM=213
  fi
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerRoom${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager"


  # Create container SENSOR ACTUATOR under ROOM- container

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerOutside_Temp${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM"

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerInside_Temp${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM"

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerWindow_position${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM"

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerDoor_position${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM"

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerPresence_detection${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM"

  echo
  echo ----------Create container under Floor_manager-AE
  curl -i -X POST -H "X-M2M-RI: req005" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=3" -d @payloadContainerAlarm${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM"


  # Create a content instance data
  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_OutsideTemp${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM/Outside_Temp"

  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_InsideTemp${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM/Inside_Temp"

  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_WindowPosition${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM/Window_Position"

  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_DoorPosition${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM/Door_Position"

  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_Presence${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM/Presence_Detection"

  echo
  echo ----------Create content instance under each container
  curl -i -X POST -H "X-M2M-RI: req008" -H "X-M2M-Origin: admin:admin" -H "Content-Type: application/xml; ty=4" -d @payloadCI_Alarm${ROOM}.xml "http://$IPADDRESS:$PORT/$CSE/in-name/Floor${FloorNum}_Manager/ROOM$ROOM/Alarm"

done
