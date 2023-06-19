
package com.javapersistence.part06.converter;

import com.javapersistence.part06.model.MonetaryAmount;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MonetaryAmountConverter
        implements AttributeConverter<MonetaryAmount, String> {

    @Override
    public String convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
        return monetaryAmount.toString();
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(String s) {
        return MonetaryAmount.fromString(s);
    }
}
