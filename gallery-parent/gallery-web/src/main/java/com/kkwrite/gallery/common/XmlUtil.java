package com.kkwrite.gallery.common;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlUtil {
	
	private static DocumentBuilderFactory dbf;
	private static DocumentBuilder db;
	
	 static {
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public synchronized static String generateTradeXml(String appid, String body, String mch_id, String nonce_str, String notify_url,
			String openid, String out_trade_no, String spbill_create_ip, String total_fee, String trade_type,
			String sign) throws Exception {
		StringBuffer xml = new StringBuffer("<xml>");
		
		xml.append("<appid>")
		   .append(appid)
		   .append("</appid>");
		
		xml.append("<body>")
		   .append(body)
		   .append("</body>");
		
		xml.append("<mch_id>")
		   .append(mch_id)
		   .append("</mch_id>");
		
		xml.append("<nonce_str>")
		   .append(nonce_str)
		   .append("</nonce_str>");
		
		xml.append("<notify_url>")
		   .append(notify_url)
		   .append("</notify_url>");
		
		xml.append("<openid>")
		   .append(openid)
		   .append("</openid>");
		
		xml.append("<out_trade_no>")
		   .append(out_trade_no)
		   .append("</out_trade_no>");
		
		xml.append("<spbill_create_ip>")
		   .append(spbill_create_ip)
		   .append("</spbill_create_ip>");
		
		xml.append("<total_fee>")
		   .append(total_fee)
		   .append("</total_fee>");
		
		xml.append("<trade_type>")
		   .append(trade_type)
		   .append("</trade_type>");
		
		xml.append("<sign>")
		   .append(sign)
		   .append("</sign>");
		
		xml.append("</xml>");
		
		return xml.toString();
	}
	
	/**
	 * 
	 * @param xml
	 * @return
	 */
	public synchronized static Map<String, String> tradeXmlToMap(String xml) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Reader reader = new StringReader(xml);
		InputSource is = new InputSource(reader);
		Document d = db.parse(is);
		Element rootEle = d.getDocumentElement();
		NodeList nodeList = rootEle.getChildNodes();
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				String key = node.getNodeName();
				String value = node.getFirstChild().getNodeValue();
				resultMap.put(key, value);
			}
		}
		return resultMap;
	}
	
	public static void main(String[] args) {
		//String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx6e6fe98f77e9d950]]></appid><mch_id><![CDATA[1488074752]]></mch_id><nonce_str><![CDATA[VEiMYhMdvvNWAVvB]]></nonce_str><sign><![CDATA[02BB8EEC6063EA315517EFE82CC831B8]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx20180116174000ba05c9359a0745158981]]></prepay_id><trade_type><![CDATA[JSAPI]]></trade_type></xml>";
		
		// notify_url
		String xml = "<xml><appid><![CDATA[wx6e6fe98f77e9d950]]></appid><bank_type><![CDATA[CFT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1488074752]]></mch_id><nonce_str><![CDATA[2]]></nonce_str><openid><![CDATA[odJuIwSif8spo3kwlx7mMluKv6RM]]></openid><out_trade_no><![CDATA[20180427202619463]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[0563C191287E7EFC599FD9CB8320119E]]></sign><time_end><![CDATA[20180427202627]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[4200000076201804277284181198]]></transaction_id></xml>";
		try {
			Map<String, String> resultMap = tradeXmlToMap(xml);
			System.out.println(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
