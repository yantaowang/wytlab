package erwan.QrCode;

/**
 * @author sunyikun
 * @version 创建时间：2019年4月11日 上午10:43:22 类说明
 */
public class QrCodeTest {
    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "www.baidu.com";
        // 嵌入二维码的图片路径
        // String imgPath = "/Users/sunyikun/Desktop/2f2e960508e6bba45a866650db1ec172.png.jpeg";
        String imgPath = "";
        // 生成的二维码的路径及名称
        String destPath = "D:\\1.jpg";
        // 生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }
}