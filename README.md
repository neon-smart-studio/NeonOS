# NeonOS
基於 Yocto 開發之智慧家庭中樞 NeonOS

進入目錄
cd NeonOS

修改編譯平台
nano build/conf/local.conf
選raspberrypi4-64

新增現有layer
source oe-init-build-env ./build
ex: bitbake-layers add-layer ./meta-openembedded/meta-networking
或是git clone -b dunfell https://git.openembedded.org/meta-openembedded

新增自訂layer
bitbake-layers create-layer ../<你的名稱>
nano meta-neon/conf/layer.conf

編譯
cd build
bitbake core-image-minimal
