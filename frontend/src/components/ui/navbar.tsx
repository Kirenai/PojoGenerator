import { Box, Heading, HStack, IconButton } from "@chakra-ui/react";
import { BiArrowBack } from "react-icons/bi";
import { Outlet, useLocation, useNavigate } from "react-router-dom";
import { ColorModeButton } from "./color-mode";

type BackIconProps = {
  pathname: string;
  handleOnClick: () => void;
};

const BackIconButton = ({ pathname, handleOnClick }: BackIconProps) => {
  if (pathname !== "/") {
    return (
      <IconButton
        variant="surface"
        colorPalette="teal"
        data-state="open"
        size="sm"
        _open={{
          animationName: "fade-in, scale-in",
          animationDuration: "300ms",
        }}
        onClick={handleOnClick}
      >
        <BiArrowBack />
      </IconButton>
    );
  }
};

const Navbar = () => {
  const { pathname } = useLocation();
  const navigate = useNavigate();

  const handleOnClick = () => {
    navigate(-1);
  };

  return (
    <>
      <HStack p="4">
        <Box position="relative" w="full" textAlign="center">
          <Heading size="4xl">Pojo Generator</Heading>
          <HStack gap="2" position="absolute" top="0" right="0">
            <BackIconButton pathname={pathname} handleOnClick={handleOnClick} />
            <ColorModeButton />
          </HStack>
        </Box>
      </HStack>
      <Outlet />
    </>
  );
};

export default Navbar;
