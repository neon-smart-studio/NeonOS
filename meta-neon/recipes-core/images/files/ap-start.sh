#!/bin/bash
ip link set wlan0 up
ip addr add 192.168.10.1/24 dev wlan0
systemctl start dnsmasq
#!/bin/sh

# 擷取 wlan0 MAC（只取後六碼，格式如: 1A2B3C）
MACADDR=$(cat /sys/class/net/wlan0/address | tr -d ':' | tail -c 7 | tr '[:lower:]' '[:upper:]')

# 動態產生 hostapd 設定檔
cat > /tmp/hostapd.conf <<EOF
interface=wlan0
driver=nl80211
ssid=NeonOS-${MACADDR}
hw_mode=g
channel=6
ieee80211n=1
ieee80211d=1
country_code=TW
wmm_enabled=1
ht_capab=[HT40+][SHORT-GI-20][DSSS_CCK-40]
auth_algs=1
EOF

# 啟動 hostapd
hostapd /tmp/hostapd.conf
