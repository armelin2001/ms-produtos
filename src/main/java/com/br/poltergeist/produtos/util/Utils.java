package com.br.poltergeist.produtos.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.IsoFields;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);
    public static final ZoneId ZONE_PADRAO = ZoneId.of("America/Sao_Paulo");
    private static final String FORMATO_PADRAO_DATA = "dd/MM/yyyy";
    private static final String FORMATO_PADRAO_HORA = "HH:mm:ss";
    private static final String FORMATO_PADRAO_DATA_HORA = FORMATO_PADRAO_DATA + " " + FORMATO_PADRAO_HORA;
    private static final String FORMATO_PADRAO_HORA_MINUTO = "HH:mm";
    private static final String FORMATO_PADRAO_DECIMAL = "0.";
    private static final String FORMATO_PADRAO_MONETARIO = "#,##0.00";
    private Utils(){}
    public static <T> List<T> copyProperties(List<?> list, Class<T> classType) {
        return list.stream().map(c -> {
            try {
                T t = classType.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(c, t);
                return t;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return null;
            }
        }).collect(Collectors.toList());
    }

    public static DateTimeFormatter instantFormatter() {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZONE_PADRAO);
    }

    public static String formatarInstantData(Instant dataHora) {
        return formatarInstant(dataHora, FORMATO_PADRAO_DATA);
    }

    public static String formatarInstantHora(Instant dataHora) {
        return formatarInstant(dataHora, FORMATO_PADRAO_HORA);
    }

    public static String formatarInstantDataHora(Instant dataHora) {
        return formatarInstant(dataHora, FORMATO_PADRAO_DATA_HORA);
    }

    public static String formatarInstantHoraMinuto(Instant dataHora) {
        return formatarInstant(dataHora, FORMATO_PADRAO_HORA_MINUTO);
    }

    public static String formatarInstantDiaMes(Instant dataHora) {
        return String.valueOf(dataHora.atZone(ZONE_PADRAO).getDayOfMonth());
    }
    public static String formatarInstant(Instant dataHora, String formato) {
        if (dataHora == null) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.isBlank(formato)) {
            throw new IllegalArgumentException("Formato é obrigatório.");
        }
        return DateTimeFormatter.ofPattern(formato).withZone(ZONE_PADRAO).format(dataHora);
    }
    public static Month getCurrentMonth() {
        return getMonth(Instant.now());
    }

    public static Month getMonth(Instant instant) {
        return instant.atZone(ZONE_PADRAO).getMonth();
    }

    public static int getCurrentDayOfMonth() {
        return Instant.now().atZone(ZONE_PADRAO).getDayOfMonth();
    }

    public static int getLengthOfCurrentMonth() {
        return getLengthOfMonth(Instant.now());
    }

    public static int getLengthOfMonth(Instant instant) {
        return YearMonth.from(instant.atZone(ZONE_PADRAO)).lengthOfMonth();
    }

    public static int getCurrentQuarterOfYear() {
        return getQuarterOfYear(Instant.now());
    }

    public static int getQuarterOfYear(Instant instant) {
        return instant.atZone(ZONE_PADRAO).get(IsoFields.QUARTER_OF_YEAR);
    }

    public static int getQuarterOfYear(Month month) {
        return month.get(IsoFields.QUARTER_OF_YEAR);
    }

    public static int getCurrentYear() {
        return Instant.now().atZone(ZONE_PADRAO).getYear();
    }

    public static String getGreetingByTime() {
        ZonedDateTime datetime = Instant.now().atZone(ZONE_PADRAO);
        if (datetime.getHour() >= 0 && datetime.getHour() <= 12) {
            return "Bom dia";
        } else if (datetime.getHour() >= 13 && datetime.getHour() <= 18) {
            return "Boa tarde";
        } else {
            return "Boa noite";
        }
    }

    /**
     * Verifica um boolean e retorna uma String vazia caso nulo ou o valor SIM e NÃO.
     */
    public static String booleanParaString(Boolean booleano, String valorPadrao) {
        if (booleano != null) {
            return booleano ? "SIM" : "NÃO";
        }
        return valorPadrao;
    }

    public static String booleanParaString(Boolean booleano, Boolean valorPadrao) {
        booleano = booleano != null ? booleano : valorPadrao;
        return booleanParaString(booleano);
    }

    public static String booleanParaString(Boolean booleano) {
        return booleanParaString(booleano, StringUtils.EMPTY);
    }

    public static Object getValueFromProperty(Object data, String propertyName) {
        Class<? extends Object> classe = data.getClass();
        try {
            Method method = Utils.getMethod(classe, propertyName);

            if (method != null) {
                return method.invoke(data, (Object[]) null);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private static Method getMethod(Class<? extends Object> classe, String propertyName) {
        try {
            return classe.getMethod("get" + StringUtils.capitalize(propertyName));
        } catch (NoSuchMethodException nme) {
            try {
                return classe.getMethod("get" + propertyName);
            } catch (NoSuchMethodException | SecurityException e) {
                return null;
            }
        }
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static String formatarNumerico(Number number, int scale) {
        if (number == null) {
            return StringUtils.EMPTY;
        }

        String formato = StringUtils.rightPad(FORMATO_PADRAO_DECIMAL, FORMATO_PADRAO_DECIMAL.length() + scale, '#');

        return number instanceof BigDecimal ? formatarNumerico((BigDecimal) number, formato)
                : StringUtils.defaultString(String.valueOf(number));
    }

    public static String formatarNumerico(BigDecimal bigDecimal, String formato) {
        if (bigDecimal == null) {
            return StringUtils.EMPTY;
        }

        if (StringUtils.isBlank(formato)) {
            throw new IllegalArgumentException("O parâmetro 'formato' é obrigatório.");
        }

        return new DecimalFormat(formato, new DecimalFormatSymbols (new Locale ("pt", "BR"))).format(bigDecimal);
    }

    public static String formatarMonetario(BigDecimal bigDecimal) {
        String monetario = formatarNumerico(bigDecimal, FORMATO_PADRAO_MONETARIO);
        if (StringUtils.isNotBlank(monetario)) {
            monetario = "R$ " + monetario;
        }
        return monetario;
    }

    public static String formatarPeso(BigDecimal bigDecimal) {
        return formatarNumerico(bigDecimal, FORMATO_PADRAO_MONETARIO);
    }

    public static Integer getIntegerOrZero(Object obj) {
        return obj instanceof Number ? ((Number) obj).intValue() : 0;
    }

    public static BigDecimal getBigDecimalOrZero(Object obj) {
        return obj instanceof BigDecimal ? (BigDecimal) obj : BigDecimal.ZERO;
    }

}

