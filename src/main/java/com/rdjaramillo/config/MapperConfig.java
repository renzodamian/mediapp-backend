package com.rdjaramillo.config;

import com.rdjaramillo.dto.ConsultDTO;
import com.rdjaramillo.dto.MedicDTO;
import com.rdjaramillo.model.Consult;
import com.rdjaramillo.model.Medic;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("defaulMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean("medicMapper")
    public ModelMapper medicMapper() {

        ModelMapper mapper = new ModelMapper();
        //Escritura
        TypeMap<MedicDTO, Medic> typeMap1 = mapper.createTypeMap(MedicDTO.class, Medic.class);
        typeMap1.addMapping(MedicDTO::getPrimaryName, (des, v)->des.setFirstName((String)v));
        typeMap1.addMapping(MedicDTO::getSurname, (des, v)->des.setLastName((String)v));
        typeMap1.addMapping(MedicDTO::getPhoto, (des, v)->des.setPhotoUrl((String)v));


        //Lectura
        TypeMap<Medic, MedicDTO> typeMap2 = mapper.createTypeMap(Medic.class, MedicDTO.class);
        typeMap2.addMapping(Medic::getFirstName, (des, v)->des.setPrimaryName((String)v));
        typeMap2.addMapping(Medic::getLastName, (des, v)->des.setSurname((String)v));


        return mapper;
    }

    @Bean("consultMapper")
    public ModelMapper consultMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<Consult, ConsultDTO> typeMap1 = mapper.createTypeMap(Consult.class, ConsultDTO.class);

        typeMap1.addMapping(e -> e.getMedic().getFirstName(), (dest, v) -> dest.getMedic().setPrimaryName((String) v));
        typeMap1.addMapping(e-> e.getMedic().getLastName(), (dest, v) -> dest.getMedic().setSurname((String) v));
        typeMap1.addMapping(e-> e.getMedic().getPhotoUrl(), (dest, v) -> dest.getMedic().setPhoto((String) v));

        return mapper;
    }

}
