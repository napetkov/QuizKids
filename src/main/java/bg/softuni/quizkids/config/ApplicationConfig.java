package bg.softuni.quizkids.config;

import bg.softuni.quizkids.models.binding.UserRegisterBindingModel;
import bg.softuni.quizkids.models.dto.UserEntityDTO;
import bg.softuni.quizkids.models.entity.UserEntity;
import bg.softuni.quizkids.models.enums.Level;
import bg.softuni.quizkids.services.RoleService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationConfig(RoleService roleService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Provider<UserEntity> newUserProvider = req -> {
            UserEntity user = new UserEntity();
            user.setRole(roleService.getRoleByName("USER"));
            user.setLevel(Level.BEGINNER);
            user.setPoint(0L);
            return user;
        };

        Converter<String, String> passwordConverter
                = ctx -> (ctx.getSource() == null)
                ? null
                : passwordEncoder.encode(ctx.getSource());

        modelMapper
                .createTypeMap(UserRegisterBindingModel.class, UserEntity.class)
                .setProvider(newUserProvider)
                .addMappings(mapper -> mapper
                        .using(passwordConverter)
                        .map(UserRegisterBindingModel::getPassword, UserEntity::setPassword));
        modelMapper.createTypeMap(UserEntity.class, UserEntityDTO.class)
                .addMapping(UserEntity::getLevel,UserEntityDTO::setLevel)
                .addMapping(UserEntity::getRole,UserEntityDTO::setRole);

        return modelMapper;
    }


}
