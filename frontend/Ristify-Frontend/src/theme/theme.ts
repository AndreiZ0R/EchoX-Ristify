import {extendTheme, theme as chakraTheme} from "@chakra-ui/react";

const colors = {
    primary: {
        base: '#845ec2',
        lighter: '#c493ff',
    },
    secondary: {
        100: '#7e5f2f'
    },
    background:{
        base: '#242424',
        darker: '#131313',
        accentDark: '#2d2d2d',
        hover: '#343434',
        dark100: '#444444',
        light: '#EFEFEF'
    },
}

const fonts = {
    heading: "Outfit, monospace",
    body: "TW Cent MT, monospace"
}

const overrides = {
    ...chakraTheme,
    colors,
    fonts
}

export const theme = extendTheme(overrides);