#!/bin/bash
ip link set wlan0 up
ip addr add 192.168.10.1/24 dev wlan0
systemctl start dnsmasq
hostapd /etc/hostapd/hostapd.conf &
