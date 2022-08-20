package com.clonelol.config;

import com.clonelol.config.property.VersionCheckInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VersionCheckDto {

    private VersionCheckInfo n;
    private String v;
    private String l;
    private String cdn;
    private String dd;
    private String lg;
    private String css;
    private int profileiconmaxcss;

}
