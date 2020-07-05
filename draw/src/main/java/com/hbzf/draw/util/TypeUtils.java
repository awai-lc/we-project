package com.hbzf.draw.util;



import com.hbzf.draw.util.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class TypeUtils {
    private static final Pattern NUMBER_WITH_TRAILING_ZEROS_PATTERN = Pattern.compile("\\.0*$");
    public TypeUtils() {}

    public static String castToString(Object value) {
        return value == null ? null : value.toString();
    }

    public static Byte castToByte(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigDecimal) {
            return byteValue((BigDecimal)value);
        } else if (value instanceof Number) {
            return ((Number)value).byteValue();
        } else if (value instanceof String) {
            String strVal = (String)value;
            return strVal.length() != 0 && !"null".equals(strVal) && !"NULL".equals(strVal) ? Byte.parseByte(strVal)
                : null;
        } else {
            throw new BizException("can not cast to byte, value : " + value);
        }
    }

    public static Character castToChar(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Character) {
            return (Character)value;
        } else if (value instanceof String) {
            String strVal = (String)value;
            if (strVal.length() == 0) {
                return null;
            } else if (strVal.length() != 1) {
                throw new BizException("can not cast to char, value : " + value);
            } else {
                return strVal.charAt(0);
            }
        } else {
            throw new BizException("can not cast to char, value : " + value);
        }
    }

    public static Short castToShort(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigDecimal) {
            return shortValue((BigDecimal)value);
        } else if (value instanceof Number) {
            return ((Number)value).shortValue();
        } else if (value instanceof String) {
            String strVal = (String)value;
            return strVal.length() != 0 && !"null".equals(strVal) && !"NULL".equals(strVal) ? Short.parseShort(strVal)
                : null;
        } else {
            throw new BizException("can not cast to short, value : " + value);
        }
    }

    public static BigDecimal castToBigDecimal(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigDecimal) {
            return (BigDecimal)value;
        } else if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger)value);
        } else {
            String strVal = value.toString();
            if (strVal.length() == 0) {
                return null;
            } else {
                return value instanceof Map && ((Map)value).size() == 0 ? null : new BigDecimal(strVal);
            }
        }
    }

    public static BigInteger castToBigInteger(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigInteger) {
            return (BigInteger)value;
        } else if (!(value instanceof Float) && !(value instanceof Double)) {
            if (value instanceof BigDecimal) {
                BigDecimal decimal = (BigDecimal)value;
                int scale = decimal.scale();
                if (scale > -1000 && scale < 1000) {
                    return ((BigDecimal)value).toBigInteger();
                }
            }

            String strVal = value.toString();
            return strVal.length() != 0 && !"null".equals(strVal) && !"NULL".equals(strVal) ? new BigInteger(strVal)
                : null;
        } else {
            return BigInteger.valueOf(((Number)value).longValue());
        }
    }

    public static Float castToFloat(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return ((Number)value).floatValue();
        } else if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() != 0 && !"null".equals(strVal) && !"NULL".equals(strVal)) {
                if (strVal.indexOf(44) != -1) {
                    strVal = strVal.replaceAll(",", "");
                }

                return Float.parseFloat(strVal);
            } else {
                return null;
            }
        } else {
            throw new BizException("can not cast to float, value : " + value);
        }
    }

    public static Double castToDouble(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return ((Number)value).doubleValue();
        } else if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() != 0 && !"null".equals(strVal) && !"NULL".equals(strVal)) {
                if (strVal.indexOf(44) != -1) {
                    strVal = strVal.replaceAll(",", "");
                }

                return Double.parseDouble(strVal);
            } else {
                return null;
            }
        } else {
            throw new BizException("can not cast to double, value : " + value);
        }
    }

    public static Date castToDate(Object value) {
        return castToDate(value, (String)null);
    }

    public static Date castToDate(Object value, String format) {
        if (value == null) {
            return null;
        } else if (value instanceof Date) {
            return (Date)value;
        } else if (value instanceof Calendar) {
            return ((Calendar)value).getTime();
        } else {
            long longValue = -1L;
            if (value instanceof BigDecimal) {
                longValue = longValue((BigDecimal)value);
                return new Date(longValue);
            } else if (value instanceof Number) {
                longValue = ((Number)value).longValue();
                if ("unixtime".equals(format)) {
                    longValue *= 1000L;
                }

                return new Date(longValue);
            } else {
                if (value instanceof String) {
                    String strVal = (String)value;
                    if (strVal.length() == 0) {
                        return null;
                    }

                    longValue = Long.parseLong(strVal);
                }

                if (longValue != -1L) {
                    return new Date(longValue);
                } else {
                    return null;
                }
            }
        }
    }

    public static java.sql.Date castToSqlDate(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof java.sql.Date) {
            return (java.sql.Date)value;
        } else if (value instanceof Date) {
            return new java.sql.Date(((Date)value).getTime());
        } else if (value instanceof Calendar) {
            return new java.sql.Date(((Calendar)value).getTimeInMillis());
        } else {
            long longValue = 0L;
            if (value instanceof BigDecimal) {
                longValue = longValue((BigDecimal)value);
            } else if (value instanceof Number) {
                longValue = ((Number)value).longValue();
            }

            if (value instanceof String) {
                String strVal = (String)value;
                if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                    return null;
                }

                if (isNumber(strVal)) {
                    longValue = Long.parseLong(strVal);
                }
            }
            if (longValue <= 0L) {
                throw new BizException("can not cast to Date, value : " + value);
            } else {
                return new java.sql.Date(longValue);
            }
        }
    }

    public static long longExtractValue(Number number) {
        return number instanceof BigDecimal ? ((BigDecimal)number).longValueExact() : number.longValue();
    }

    public static Timestamp castToTimestamp(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Calendar) {
            return new Timestamp(((Calendar)value).getTimeInMillis());
        } else if (value instanceof Timestamp) {
            return (Timestamp)value;
        } else if (value instanceof Date) {
            return new Timestamp(((Date)value).getTime());
        } else {
            long longValue = 0L;
            if (value instanceof BigDecimal) {
                longValue = longValue((BigDecimal)value);
            } else if (value instanceof Number) {
                longValue = ((Number)value).longValue();
            }

            if (value instanceof String) {
                String strVal = (String)value;
                if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                    return null;
                }
                if (isNumber(strVal)) {
                    longValue = Long.parseLong(strVal);
                }
            }

            if (longValue <= 0L) {
                throw new BizException("can not cast to Timestamp, value : " + value);
            } else {
                return new Timestamp(longValue);
            }
        }
    }

    static int num(char c0, char c1) {
        return c0 >= '0' && c0 <= '9' && c1 >= '0' && c1 <= '9' ? (c0 - 48) * 10 + (c1 - 48) : -1;
    }

    static int num(char c0, char c1, char c2, char c3) {
        return c0 >= '0' && c0 <= '9' && c1 >= '0' && c1 <= '9' && c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9'
            ? (c0 - 48) * 1000 + (c1 - 48) * 100 + (c2 - 48) * 10 + (c3 - 48) : -1;
    }

    static int num(char c0, char c1, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        return c0 >= '0' && c0 <= '9' && c1 >= '0' && c1 <= '9' && c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9'
            && c4 >= '0' && c4 <= '9' && c5 >= '0' && c5 <= '9' && c6 >= '0' && c6 <= '9' && c7 >= '0' && c7 <= '9'
            && c8 >= '0' && c8 <= '9'
                ? (c0 - 48) * 100000000 + (c1 - 48) * 10000000 + (c2 - 48) * 1000000 + (c3 - 48) * 100000
                    + (c4 - 48) * 10000 + (c5 - 48) * 1000 + (c6 - 48) * 100 + (c7 - 48) * 10 + (c8 - 48)
                : -1;
    }

    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (ch != '+' && ch != '-') {
                if (ch < '0' || ch > '9') {
                    return false;
                }
            } else if (i != 0) {
                return false;
            }
        }

        return true;
    }

    public static Long castToLong(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigDecimal) {
            return longValue((BigDecimal)value);
        } else if (value instanceof Number) {
            return ((Number)value).longValue();
        } else {
            if (value instanceof String) {
                String strVal = (String)value;
                if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                    return null;
                }

                if (strVal.indexOf(44) != -1) {
                    strVal = strVal.replaceAll(",", "");
                }
                return Long.parseLong(strVal);
            }

            if (value instanceof Map) {
                Map map = (Map)value;
                if (map.size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
                    Iterator iter = map.values().iterator();
                    iter.next();
                    Object value2 = iter.next();
                    return castToLong(value2);
                }
            }
            throw new BizException("can not cast to long, value : " + value);
        }
    }

    public static byte byteValue(BigDecimal decimal) {
        if (decimal == null) {
            return 0;
        } else {
            int scale = decimal.scale();
            return scale >= -100 && scale <= 100 ? decimal.byteValue() : decimal.byteValueExact();
        }
    }

    public static short shortValue(BigDecimal decimal) {
        if (decimal == null) {
            return 0;
        } else {
            int scale = decimal.scale();
            return scale >= -100 && scale <= 100 ? decimal.shortValue() : decimal.shortValueExact();
        }
    }

    public static int intValue(BigDecimal decimal) {
        if (decimal == null) {
            return 0;
        } else {
            int scale = decimal.scale();
            return scale >= -100 && scale <= 100 ? decimal.intValue() : decimal.intValueExact();
        }
    }

    public static long longValue(BigDecimal decimal) {
        if (decimal == null) {
            return 0L;
        } else {
            int scale = decimal.scale();
            return scale >= -100 && scale <= 100 ? decimal.longValue() : decimal.longValueExact();
        }
    }

    public static Integer castToInt(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Integer) {
            return (Integer)value;
        } else if (value instanceof BigDecimal) {
            return intValue((BigDecimal)value);
        } else if (value instanceof Number) {
            return ((Number)value).intValue();
        } else if (value instanceof String) {
            String strVal = (String)value;
            if (strVal.length() != 0 && !"null".equals(strVal) && !"NULL".equals(strVal)) {
                if (strVal.indexOf(44) != -1) {
                    strVal = strVal.replaceAll(",", "");
                }

                Matcher matcher = NUMBER_WITH_TRAILING_ZEROS_PATTERN.matcher(strVal);
                if (matcher.find()) {
                    strVal = matcher.replaceAll("");
                }

                return Integer.parseInt(strVal);
            } else {
                return null;
            }
        } else if (value instanceof Boolean) {
            return (Boolean)value ? 1 : 0;
        } else {
            if (value instanceof Map) {
                Map map = (Map)value;
                if (map.size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
                    Iterator iter = map.values().iterator();
                    iter.next();
                    Object value2 = iter.next();
                    return castToInt(value2);
                }
            }

            throw new BizException("can not cast to int, value : " + value);
        }
    }

    public static byte[] castToBytes(Object value) {
        if (value instanceof byte[]) {
            return (byte[])((byte[])value);
        } else if (value instanceof String) {
            return ((String) value).getBytes();
        } else {
            throw new BizException("can not cast to byte[], value : " + value);
        }
    }

    public static Boolean castToBoolean(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Boolean) {
            return (Boolean)value;
        } else if (value instanceof BigDecimal) {
            return intValue((BigDecimal)value) == 1;
        } else if (value instanceof Number) {
            return ((Number)value).intValue() == 1;
        } else if (value instanceof String) {
            String strVal = (String)value;
            if (strVal.length() != 0 && !"null".equals(strVal) && !"NULL".equals(strVal)) {
                if (!"true".equalsIgnoreCase(strVal) && !"1".equals(strVal)) {
                    if (!"false".equalsIgnoreCase(strVal) && !"0".equals(strVal)) {
                        if (!"Y".equalsIgnoreCase(strVal) && !"T".equals(strVal)) {
                            if (!"F".equalsIgnoreCase(strVal) && !"N".equals(strVal)) {
                                throw new BizException("can not cast to boolean, value : " + value);
                            } else {
                                return Boolean.FALSE;
                            }
                        } else {
                            return Boolean.TRUE;
                        }
                    } else {
                        return Boolean.FALSE;
                    }
                } else {
                    return Boolean.TRUE;
                }
            } else {
                return null;
            }
        } else {
            throw new BizException("can not cast to boolean, value : " + value);
        }
    }

}
