import React from 'react';
import {Select, FormControl, FormLabel} from '@chakra-ui/react';

interface CountrySelectProps {
    label: string;
    onChange: (e: React.ChangeEvent<HTMLSelectElement>) => void;
    value: string;
    placeholder: string;
    size: string;
    borderRadius: string;
    backgroundColor: string;
    color: string;
    hoverBackgroundColor: string;
    selectionBackgroundColor: string;
    onBlur?: (e: React.FocusEvent<HTMLSelectElement>) => void;
}

const CountrySelect = ({
                           label, onChange, value, placeholder, size, borderRadius, backgroundColor, color,
                           hoverBackgroundColor, selectionBackgroundColor, onBlur
                       }: CountrySelectProps) => {
    return (
        <FormControl id="country" isRequired pb={10}>
            <FormLabel border="none" outline="none" color="primary.lighter" fontSize="2xl"
                       sx={{
                           userSelect: 'none',
                           pointerEvents: 'none',
                           textDecoration: 'none'
                       }}
            >{label}</FormLabel>
            <Select
                _hover={{borderColor: "transparent", border:'none', outline: 'none', backgroundColor: hoverBackgroundColor}}
                _focus={{borderColor: "transparent", border:'none', outline: 'none', boxShadow: 'none'}}
                onBlur={onBlur}
                size={size}
                outline="none"
                border="none"
                borderRadius={borderRadius}
                backgroundColor={backgroundColor}
                color={color}
                onChange={onChange}
                value={value}
                sx={{
                    color: 'background.light',
                    bg: 'background.base',
                    'option': {
                        bg: 'background.base',
                        color: 'background.light',
                    },
                    'option:hover, option:focus': {
                        bg: {selectionBackgroundColor},
                        color: 'background.light'
                    },
                    '::-webkit-scrollbar': {
                        width: '4px',
                        borderRadius: '8px',
                    },
                    '::-webkit-scrollbar-track': {
                        background: 'background.darker',
                    },
                    '::-webkit-scrollbar-thumb': {
                        background: 'primary.base',
                        borderRadius: '8px',
                    },
                    '::-webkit-scrollbar-thumb:hover': {
                        background: 'primary.darker',
                    }
                }}
            >
                <option value="">{placeholder}</option>
                <option value="usa">United States</option>
                <option value="romania">Romania</option>
                <option value="canada">Canada</option>
                <option value="uk">United Kingdom</option>
                <option value="germany">Germany</option>
                <option value="france">France</option>
                <option value="italy">Italy</option>
                <option value="spain">Spain</option>
                <option value="japan">Japan</option>
                <option value="australia">Australia</option>
                <option value="india">India</option>
                <option value="brazil">Brazil</option>
                <option value="mexico">Mexico</option>
                <option value="south-africa">South Africa</option>
                <option value="russia">Russia</option>
                <option value="china">China</option>
                <option value="south-korea">South Korea</option>
                <option value="netherlands">Netherlands</option>
                <option value="sweden">Sweden</option>
                <option value="norway">Norway</option>
                <option value="finland">Finland</option>
                <option value="denmark">Denmark</option>
                <option value="poland">Poland</option>
                <option value="argentina">Argentina</option>
                <option value="chile">Chile</option>
                <option value="egypt">Egypt</option>
                <option value="turkey">Turkey</option>
                <option value="thailand">Thailand</option>
                <option value="malaysia">Malaysia</option>
                <option value="singapore">Singapore</option>
                <option value="new-zealand">New Zealand</option>
                <option value="portugal">Portugal</option>
                <option value="greece">Greece</option>
                <option value="belgium">Belgium</option>
                <option value="austria">Austria</option>
                <option value="switzerland">Switzerland</option>
            </Select>
        </FormControl>
    );
};

export default CountrySelect;
