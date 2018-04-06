package sasho.io;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import sasho.entities.dto.exercise02.FullUserInfoDto;
import sasho.entities.orm.User;

@Component
public class ModelParserImpl implements sasho.io.interfaces.ModelParser
{
    private ModelMapper modelMapper;

    public ModelParserImpl()
    {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public <S, D> D parse(S source, Class<D> destinationClass)
    {
        D convertedObject = null;
        convertedObject = this.modelMapper.map(source, destinationClass);
        return convertedObject;
    }
}