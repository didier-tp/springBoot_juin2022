package tp.appliSpring.util;

import org.springframework.beans.BeanUtils;
import tp.appliSpring.dto.CompteDto;
import tp.appliSpring.entity.Compte;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {

    public static Compte compteDtoToCompte(CompteDto compteDto){
        Compte compte = new Compte();
        BeanUtils.copyProperties(compteDto,compte);
        return compte;
    }

    public static CompteDto compteToCompteDto(Compte compte){
        //return new CompteDto(compte.getNumero(), compte.getLabel(), compte.getSolde());
        CompteDto compteDto = new CompteDto();
        /*
        compteDto.setNumero(compte.getNumero());
        compteDto.setLabel(compte.getLabel());
        compteDto.setSolde(compte.getSolde());
         */
        BeanUtils.copyProperties(compte,compteDto);
        return compteDto;
    }

    public static List<CompteDto> compteListToCompteDtoList(List<Compte> comptes){
        return comptes.stream()
                .map( (compte) -> compteToCompteDto(compte))
                .collect(Collectors.toList());
    }

}
