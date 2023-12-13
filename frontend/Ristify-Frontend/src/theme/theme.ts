import {extendTheme, theme as chakraTheme} from "@chakra-ui/react";

const colors = {
    primary: {
        100: '#2ef45f'
    },
    secondary: {
        100: '#7e5f2f'
    }
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
