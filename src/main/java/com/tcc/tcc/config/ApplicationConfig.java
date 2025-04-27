package com.tcc.tcc.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tcc.tcc.common.DateConverter;
import com.tcc.tcc.domain.dto.user.UserRequestDTO;
import com.tcc.tcc.domain.dto.user.UserResponseDTO;
import com.tcc.tcc.domain.model.User;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, Date> stringToDateConverter = new Converter<String, Date>() {

            @Override
            public Date convert(MappingContext<String, Date> context) {
                return DateConverter.stringToDate(context.getSource());

            }

        };

        Converter<Date, String> dateToStringConverter = new Converter<Date, String>() {

            @Override
            public String convert(MappingContext<Date, String> context) {
                return DateConverter.dateToString(context.getSource());
            }

        };

        PropertyMap<User, UserResponseDTO> userToDTOPropertyMap = new PropertyMap<User, UserResponseDTO>() {

            @Override
            protected void configure() {
                using(dateToStringConverter).map(source.getTime()).setTime(null);
            }

        };

        PropertyMap<UserRequestDTO, User> dtoToUserPropertyMap = new PropertyMap<UserRequestDTO, User>() {
            @Override
            protected void configure() {
                using(stringToDateConverter).map(source.getTime()).setTime(null);
            }
        };

        modelMapper.addMappings(userToDTOPropertyMap);
        modelMapper.addMappings(dtoToUserPropertyMap);
        return modelMapper;
    }

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        // return new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
    }
}
