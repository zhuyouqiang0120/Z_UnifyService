/**
 * TrainTimeWebServiceSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.WebXml;

import java.io.Serializable;
import java.util.Enumeration;

import javax.xml.namespace.QName;

import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.EnumDeserializerFactory;
import org.apache.axis.encoding.ser.EnumSerializerFactory;
import org.apache.axis.encoding.ser.SimpleDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListSerializerFactory;
import org.apache.axis.encoding.ser.SimpleSerializerFactory;

public class TrainTimeWebServiceSoapStub extends org.apache.axis.client.Stub
		implements TrainTimeWebServiceSoap {
	private java.util.Vector<Class> cachedSerClasses = new java.util.Vector<Class>();
	private java.util.Vector<QName> cachedSerQNames = new java.util.Vector<QName>();
	private java.util.Vector<Serializable> cachedSerFactories = new java.util.Vector<Serializable>();
	private java.util.Vector<Serializable> cachedDeserFactories = new java.util.Vector<Serializable>();

	static OperationDesc[] _operations;

	static {
		_operations = new OperationDesc[8];
		_initOperationDesc1();
	}

	private static void _initOperationDesc1() {
		OperationDesc oper;
		ParameterDesc param;
		oper = new OperationDesc();
		oper.setName("getStationNameDataSet");
		oper.setReturnType(new QName("http://WebXml.com.cn/", ">>getStationNameDataSetResponse>getStationNameDataSetResult"));
		oper.setReturnClass(GetStationNameDataSetResponseGetStationNameDataSetResult.class);
		oper.setReturnQName(new QName("http://WebXml.com.cn/", "getStationNameDataSetResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;

		oper = new OperationDesc();
		oper.setName("getStationAndTimeByTrainCode");
		param = new ParameterDesc(new QName("http://WebXml.com.cn/","TrainCode"),
				ParameterDesc.IN,
				new QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new ParameterDesc(
				new QName("http://WebXml.com.cn/", "UserID"),
				ParameterDesc.IN,
				new QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new QName(
				"http://WebXml.com.cn/", "ArrayOfString"));
		oper.setReturnClass(String[].class);
		oper.setReturnQName(new QName(
				"http://WebXml.com.cn/", "getStationAndTimeByTrainCodeResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new QName(
				"http://WebXml.com.cn/", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1] = oper;

		oper = new OperationDesc();
		oper.setName("getVersionTime");
		oper.setReturnType(new QName(
				"http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName(
				"http://WebXml.com.cn/", "getVersionTimeResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2] = oper;

		oper = new OperationDesc();
		oper.setName("getStationAndTimeDataSetByTrainCode");
		param = new ParameterDesc(new QName("http://WebXml.com.cn/","TrainCode"),ParameterDesc.IN,new QName("http://www.w3.org/2001/XMLSchema", "string"),String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new ParameterDesc(
				new QName("http://WebXml.com.cn/", "UserID"),
				ParameterDesc.IN,
				new QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new QName(
				"http://WebXml.com.cn/",
				">>getStationAndTimeDataSetByTrainCodeResponse>getStationAndTimeDataSetByTrainCodeResult"));
		oper.setReturnClass(GetStationAndTimeDataSetByTrainCodeResponseGetStationAndTimeDataSetByTrainCodeResult.class);
		oper.setReturnQName(new QName(
				"http://WebXml.com.cn/",
				"getStationAndTimeDataSetByTrainCodeResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3] = oper;

		oper = new OperationDesc();
		oper.setName("getStationAndTimeDataSetByLikeTrainCode");
		param = new ParameterDesc(
				new QName("http://WebXml.com.cn/",
						"TrainCode"),
				ParameterDesc.IN,
				new QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new ParameterDesc(
				new QName("http://WebXml.com.cn/", "UserID"),
				ParameterDesc.IN,
				new QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new QName(
				"http://WebXml.com.cn/",
				">>getStationAndTimeDataSetByLikeTrainCodeResponse>getStationAndTimeDataSetByLikeTrainCodeResult"));
		oper.setReturnClass(GetStationAndTimeDataSetByLikeTrainCodeResponseGetStationAndTimeDataSetByLikeTrainCodeResult.class);
		oper.setReturnQName(new QName(
				"http://WebXml.com.cn/",
				"getStationAndTimeDataSetByLikeTrainCodeResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[4] = oper;

		oper = new OperationDesc();
		oper.setName("getDetailInfoByTrainCode");
		param = new ParameterDesc(
				new QName("http://WebXml.com.cn/",
						"TrainCode"),
				ParameterDesc.IN,
				new QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new ParameterDesc(
				new QName("http://WebXml.com.cn/", "UserID"),
				ParameterDesc.IN,
				new QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new QName(
				"http://WebXml.com.cn/",
				">>getDetailInfoByTrainCodeResponse>getDetailInfoByTrainCodeResult"));
		oper.setReturnClass(GetDetailInfoByTrainCodeResponseGetDetailInfoByTrainCodeResult.class);
		oper.setReturnQName(new QName(
				"http://WebXml.com.cn/", "getDetailInfoByTrainCodeResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[5] = oper;

		oper = new OperationDesc();
		oper.setName("getStationName");
		oper.setReturnType(new QName(
				"http://WebXml.com.cn/", "ArrayOfString"));
		oper.setReturnClass(String[].class);
		oper.setReturnQName(new QName(
				"http://WebXml.com.cn/", "getStationNameResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new QName(
				"http://WebXml.com.cn/", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[6] = oper;

		oper = new OperationDesc();
		oper.setName("getStationAndTimeByStationName");
		param = new ParameterDesc(
				new QName("http://WebXml.com.cn/",
						"StartStation"),
				ParameterDesc.IN,
				new QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new ParameterDesc(
				new QName("http://WebXml.com.cn/",
						"ArriveStation"),
				ParameterDesc.IN,
				new QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("http://WebXml.com.cn/", "UserID"),
				ParameterDesc.IN,
				new QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new QName(
				"http://WebXml.com.cn/",
				">>getStationAndTimeByStationNameResponse>getStationAndTimeByStationNameResult"));
		oper.setReturnClass(GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult.class);
		oper.setReturnQName(new QName(
				"http://WebXml.com.cn/", "getStationAndTimeByStationNameResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[7] = oper;

	}

	public TrainTimeWebServiceSoapStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public TrainTimeWebServiceSoapStub(java.net.URL endpointURL,
			javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public TrainTimeWebServiceSoapStub(javax.xml.rpc.Service service)
			throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service)
				.setTypeMappingVersion("1.2");
		Class cls;
		QName qName;
		QName qName2;
		Class<BeanSerializerFactory> beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		Class<BeanDeserializerFactory> beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		Class<EnumSerializerFactory> enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		Class<EnumDeserializerFactory> enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		Class<ArraySerializerFactory> arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
		Class<ArrayDeserializerFactory> arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
		Class<SimpleSerializerFactory> simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
		Class<SimpleDeserializerFactory> simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
		Class<SimpleListSerializerFactory> simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
		Class<SimpleListDeserializerFactory> simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
		qName = new QName("http://WebXml.com.cn/",
				">>getDetailInfoByTrainCodeResponse>getDetailInfoByTrainCodeResult");
		cachedSerQNames.add(qName);
		cls = GetDetailInfoByTrainCodeResponseGetDetailInfoByTrainCodeResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">>getStationAndTimeByStationNameResponse>getStationAndTimeByStationNameResult");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
				"http://WebXml.com.cn/",
				">>getStationAndTimeDataSetByLikeTrainCodeResponse>getStationAndTimeDataSetByLikeTrainCodeResult");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeDataSetByLikeTrainCodeResponseGetStationAndTimeDataSetByLikeTrainCodeResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
				"http://WebXml.com.cn/",
				">>getStationAndTimeDataSetByTrainCodeResponse>getStationAndTimeDataSetByTrainCodeResult");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeDataSetByTrainCodeResponseGetStationAndTimeDataSetByTrainCodeResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">>getStationNameDataSetResponse>getStationNameDataSetResult");
		cachedSerQNames.add(qName);
		cls = GetStationNameDataSetResponseGetStationNameDataSetResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">DataSet");
		cachedSerQNames.add(qName);
		cls = DataSet.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getDetailInfoByTrainCode");
		cachedSerQNames.add(qName);
		cls = GetDetailInfoByTrainCode.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getDetailInfoByTrainCodeResponse");
		cachedSerQNames.add(qName);
		cls = GetDetailInfoByTrainCodeResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getStationAndTimeByStationName");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeByStationName.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getStationAndTimeByStationNameResponse");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeByStationNameResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getStationAndTimeByTrainCode");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeByTrainCode.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getStationAndTimeByTrainCodeResponse");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeByTrainCodeResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getStationAndTimeDataSetByLikeTrainCode");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeDataSetByLikeTrainCode.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getStationAndTimeDataSetByLikeTrainCodeResponse");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeDataSetByLikeTrainCodeResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getStationAndTimeDataSetByTrainCode");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeDataSetByTrainCode.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getStationAndTimeDataSetByTrainCodeResponse");
		cachedSerQNames.add(qName);
		cls = GetStationAndTimeDataSetByTrainCodeResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getStationName");
		cachedSerQNames.add(qName);
		cls = GetStationName.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getStationNameResponse");
		cachedSerQNames.add(qName);
		cls = GetStationNameResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/",
				">getVersionTime");
		cachedSerQNames.add(qName);
		cls = GetVersionTime.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/", ">getVersionTimeResponse");
		cachedSerQNames.add(qName);
		cls = GetVersionTimeResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName("http://WebXml.com.cn/", "ArrayOfString");
		cachedSerQNames.add(qName);
		cls = String[].class;
		cachedSerClasses.add(cls);
		qName = new QName("http://www.w3.org/2001/XMLSchema", "string");
		qName2 = new QName("http://WebXml.com.cn/", "string");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

	}

	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet) {
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				_call.setPortName(super.cachedPortName);
			}
			Enumeration<Object> keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized (this) {
				if (firstCall()) {
					// must set encoding style before registering serializers
					_call.setEncodingStyle(null);
					for (int i = 0; i < cachedSerFactories.size(); ++i) {
						Class cls = (Class) cachedSerClasses
								.get(i);
						QName qName = (QName) cachedSerQNames
								.get(i);
						Object x = cachedSerFactories.get(i);
						if (x instanceof Class) {
							Class sf = (Class) cachedSerFactories.get(i);
							Class df = (Class) cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						} else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories
									.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories
									.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}
			return _call;
		} catch (Throwable _t) {
			throw new org.apache.axis.AxisFault(
					"Failure trying to get the Call object", _t);
		}
	}

	public GetStationNameDataSetResponseGetStationNameDataSetResult getStationNameDataSet() throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://WebXml.com.cn/getStationNameDataSet");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName(
				"http://WebXml.com.cn/", "getStationNameDataSet"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] {});

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (GetStationNameDataSetResponseGetStationNameDataSetResult) _resp;
				} catch (Exception _exception) {
					return (GetStationNameDataSetResponseGetStationNameDataSetResult) org.apache.axis.utils.JavaUtils
							.convert(
									_resp,
									GetStationNameDataSetResponseGetStationNameDataSetResult.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String[] getStationAndTimeByTrainCode(String trainCode, String userID)	throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://WebXml.com.cn/getStationAndTimeByTrainCode");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName(
				"http://WebXml.com.cn/", "getStationAndTimeByTrainCode"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] {
					trainCode, userID });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String[]) _resp;
				} catch (Exception _exception) {
					return (String[]) org.apache.axis.utils.JavaUtils
							.convert(_resp, String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String getVersionTime() throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://WebXml.com.cn/getVersionTime");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName(
				"http://WebXml.com.cn/", "getVersionTime"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] {});

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) org.apache.axis.utils.JavaUtils
							.convert(_resp, String.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public GetStationAndTimeDataSetByTrainCodeResponseGetStationAndTimeDataSetByTrainCodeResult getStationAndTimeDataSetByTrainCode(
			String trainCode, String userID)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://WebXml.com.cn/getStationAndTimeDataSetByTrainCode");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName(
				"http://WebXml.com.cn/", "getStationAndTimeDataSetByTrainCode"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] {
					trainCode, userID });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (GetStationAndTimeDataSetByTrainCodeResponseGetStationAndTimeDataSetByTrainCodeResult) _resp;
				} catch (Exception _exception) {
					return (GetStationAndTimeDataSetByTrainCodeResponseGetStationAndTimeDataSetByTrainCodeResult) org.apache.axis.utils.JavaUtils
							.convert(
									_resp,
									GetStationAndTimeDataSetByTrainCodeResponseGetStationAndTimeDataSetByTrainCodeResult.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public GetStationAndTimeDataSetByLikeTrainCodeResponseGetStationAndTimeDataSetByLikeTrainCodeResult getStationAndTimeDataSetByLikeTrainCode(
			String trainCode, String userID)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://WebXml.com.cn/getStationAndTimeDataSetByLikeTrainCode");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName(
				"http://WebXml.com.cn/",
				"getStationAndTimeDataSetByLikeTrainCode"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] {
					trainCode, userID });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (GetStationAndTimeDataSetByLikeTrainCodeResponseGetStationAndTimeDataSetByLikeTrainCodeResult) _resp;
				} catch (Exception _exception) {
					return (GetStationAndTimeDataSetByLikeTrainCodeResponseGetStationAndTimeDataSetByLikeTrainCodeResult) org.apache.axis.utils.JavaUtils
							.convert(
									_resp,
									GetStationAndTimeDataSetByLikeTrainCodeResponseGetStationAndTimeDataSetByLikeTrainCodeResult.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public GetDetailInfoByTrainCodeResponseGetDetailInfoByTrainCodeResult getDetailInfoByTrainCode(String trainCode, String userID)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://WebXml.com.cn/getDetailInfoByTrainCode");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName(
				"http://WebXml.com.cn/", "getDetailInfoByTrainCode"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] {
					trainCode, userID });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (GetDetailInfoByTrainCodeResponseGetDetailInfoByTrainCodeResult) _resp;
				} catch (Exception _exception) {
					return (GetDetailInfoByTrainCodeResponseGetDetailInfoByTrainCodeResult) org.apache.axis.utils.JavaUtils
							.convert(
									_resp,
									GetDetailInfoByTrainCodeResponseGetDetailInfoByTrainCodeResult.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String[] getStationName() throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://WebXml.com.cn/getStationName");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://WebXml.com.cn/", "getStationName"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] {});

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String[]) _resp;
				} catch (Exception _exception) {
					return (String[]) org.apache.axis.utils.JavaUtils
							.convert(_resp, String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult getStationAndTimeByStationName(
			String startStation, String arriveStation,
			String userID) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[7]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://WebXml.com.cn/getStationAndTimeByStationName");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://WebXml.com.cn/", "getStationAndTimeByStationName"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] {
					startStation, arriveStation, userID });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult) _resp;
				} catch (Exception _exception) {
					return (GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult) org.apache.axis.utils.JavaUtils
							.convert(
									_resp,
									GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

}
