import React from 'react';
import { FormControl, FormLabel, InputGroup, Input } from '@chakra-ui/react';

interface EmailInputProps {
    label: string;
    size: string;
    borderRadius: string;
    type: string;
    name: string;
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    onBlur?: (e: React.FocusEvent<HTMLInputElement>) => void; // Added onBlur prop
    value: string;
    backgroundColor: string;
    color: string;
    hoverBackgroundColor: string;
    selectionBackgroundColor: string;
    focusBoxShadow: string;
    placeholderStyle: {
        color: string;
        fontSize: string;
    };
    width: string;
    fontSize: string;
    fontFamily: string;
    autoComplete?: string;
    sx?: React.CSSProperties;
}

const CustomInput = React.forwardRef<HTMLInputElement, EmailInputProps>(
    (
        {
            label,
            size,
            borderRadius,
            type,
            name,
            onChange,
            onBlur, // Added onBlur prop
            value,
            backgroundColor,
            color,
            hoverBackgroundColor,
            selectionBackgroundColor,
            placeholderStyle,
            width,
            fontSize,
            fontFamily,
            autoComplete,
            sx,
        },
        ref
    ) => {
        return (
            <FormControl id={name} isRequired pb={5}>
                <FormLabel
                    border="none"
                    color="primary.lighter"
                    fontSize="2xl"
                    sx={{
                        userSelect: 'none',
                        pointerEvents: 'none',
                        textDecoration: 'none',
                    }}
                >
                    {label}
                </FormLabel>
                <InputGroup border="none" size={size}>
                    <Input
                        ref={ref}
                        borderColor="transparent"
                        _hover={{
                            borderColor: 'transparent',
                            backgroundColor: hoverBackgroundColor,
                        }}
                        _focus={{
                            borderColor: 'transparent',
                            outline: 'none',
                            boxShadow: 'none',
                        }}
                        _selection={{
                            outline: 'none',
                            borderColor: 'transparent',
                            backgroundColor: selectionBackgroundColor,
                        }}
                        borderRadius={borderRadius}
                        type={type}
                        name={name}
                        onChange={onChange}
                        onBlur={onBlur}
                        value={value}
                        backgroundColor={backgroundColor}
                        color={color}
                        sx={{ ...sx, '::placeholder': placeholderStyle }}
                        w={width}
                        fontSize={fontSize}
                        fontFamily={fontFamily}
                        autoComplete={autoComplete}
                    />
                </InputGroup>
            </FormControl>
        );
    }
);

export default CustomInput;
